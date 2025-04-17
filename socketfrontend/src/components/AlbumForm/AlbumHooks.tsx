import { ChangeEvent, FormEvent, useState } from "react";
import { processSelectedFiles, uploadAlbum } from "./AlbumServices";

const useAlbumForm = () => {
  const [albumName, setAlbumName] = useState("");
  const [selectedFiles, setSelectedFiles] = useState<
    { file: File; previewImg: string }[]
  >([]);

  const handleFileChange = (event: ChangeEvent<HTMLInputElement>) => {
    if (event.target.files) {
      const filesArray = processSelectedFiles(Array.from(event.target.files));

      setSelectedFiles(filesArray);
    }
  };

  const removeImage = (index: number) => {
    const updatedImages = selectedFiles.filter((_, i) => i !== index);
    setSelectedFiles(updatedImages);
  };

  const formIsValid = () =>
    albumName.trim().length > 3 && selectedFiles.length > 0;

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    if (!formIsValid()) return;

    try {
      await uploadAlbum(albumName, selectedFiles);
    } catch (error: any) {
      console.log(error);
    }
  };

  return {
    albumName,
    setAlbumName,
    selectedFiles,
    setSelectedFiles,
    handleFileChange,
    removeImage,
    handleSubmit,
    formIsValid,
  };
};

export { useAlbumForm };
