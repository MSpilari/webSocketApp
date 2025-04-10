import React from "react";

import { useAlbumForm } from "./AlbumHooks";
import "./styles.css";

const AlbumForm: React.FC = () => {
  const {
    albumName,
    setAlbumName,
    selectedFiles,
    handleFileChange,
    formIsValid,
    handleSubmit,
    removeImage,
  } = useAlbumForm();

  return (
    <form onSubmit={(e) => handleSubmit(e)} className="album-form">
      <h1>Create your album</h1>
      <label htmlFor="albumName">Album Name:</label>
      <input
        type="text"
        id="albumName"
        value={albumName}
        onChange={(e) => setAlbumName(e.target.value)}
        required
      />

      <label className="custom-file-label" htmlFor="images">
        Select Images
      </label>
      <input
        className="hidden-file-input"
        type="file"
        id="images"
        multiple
        accept="image/*"
        onChange={(e) => handleFileChange(e)}
      />
      <div className="previewImgContainer">
        {selectedFiles.map((obj, index) => (
          <div className="imgWrapper">
            <img key={index} className="previewImg" src={obj.previewImg} />
            <button
              type="button"
              className="eraseImg"
              onClick={() => removeImage(index)}
            >
              X
            </button>
          </div>
        ))}
      </div>

      <button
        type="submit"
        disabled={!formIsValid()}
        className={formIsValid() ? "activeBtn" : "disabledBtn"}
      >
        Upload Album
      </button>
    </form>
  );
};

export default AlbumForm;
